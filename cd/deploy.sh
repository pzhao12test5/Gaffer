#!/usr/bin/env bash

set -e

if [ "$RELEASE" == 'true' ] && [ "$TRAVIS_BRANCH" == 'master' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    git checkout master
    mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate -Dexpression=project.version
    POM_VERSION=`mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate -Dexpression=project.version | grep -v '\['`
    echo "POM_VERSION = $POM_VERSION"
    if [[ "$POM_VERSION" == *SNAPSHOT ]]; then
        if [ -z "$GITHUB_TOKEN" ]; then
            echo "GITHUB_TOKEN has not been set. Please configure this in Travis CI settings"
            exit 1
        fi

        if [ -z "$RELEASE_VERSION" ]; then
            RELEASE_VERSION=${POM_VERSION%-SNAPSHOT}
        fi

        echo ""
        echo "======================================"
        echo "Tagging and releasing version $RELEASE_VERSION"
        echo "======================================"
        echo ""

        # Configure GitHub token
        git config --global credential.helper "store --file=.git/credentials"
        echo "https://${GITHUB_TOKEN}:@github.com" > .git/credentials

        echo ""
        echo "--------------------------------------"
        echo "Tagging version $RELEASE_VERSION"
        echo "--------------------------------------"
        mvn versions:set -DnewVersion=$RELEASE_VERSION -DgenerateBackupPoms=false
        git commit -a -m "prepare release gaffer-$RELEASE_VERSION"
        git push
        git tag gaffer2-$RELEASE_VERSION
        git push origin --tags

        echo ""
        echo "--------------------------------------"
        echo "Updating javadoc"
        echo "--------------------------------------"
        mvn -q clean install -Pquick -Dskip.jar-with-dependencies=true -Dshaded.jar.phase=true
        mvn -q javadoc:javadoc -Pquick
        mvn -q scm-publish:publish-scm -Dmaven.javadoc.failOnError=false -Pquick

        echo ""
        echo "--------------------------------------"
        echo "Creating GitHub release notes"
        echo "--------------------------------------"
        JSON_DATA="{
                \"tag_name\": \"gaffer-$RELEASE_VERSION\",
                \"name\": \"Gaffer $RELEASE_VERSION\",
                \"body\": \"[$RELEASE_VERSION issues resolved](https://github.com/gchq/Gaffer/issues?q=milestone%3Av$RELEASE_VERSION)\n\n[$RELEASE_VERSION issues with migration steps](https://github.com/gchq/Gaffer/issues?q=milestone%3Av$RELEASE_VERSION+label%3Amigration-required)\",
                \"draft\": false
            }"
        echo $JSON_DATA
        curl -v --data "$JSON_DATA" https://api.github.com/repos/gchq/Gaffer/releases?access_token=$GITHUB_TOKEN

        echo ""
        echo "--------------------------------------"
        echo "Merging into develop and updating pom version"
        echo "--------------------------------------"
        git checkout develop
        git pull
        git merge master
        mvn release:update-versions -B
        git commit -a -m "prepare for next development iteration"
        git push
    else
        echo ""
        echo "======================================"
        echo "Tagging and releasing version $POM_VERSION"
        echo "======================================"
        echo ""

        openssl aes-256-cbc -K $encrypted_de949738249f_key -iv $encrypted_de949738249f_iv -in cd/codesigning.asc.enc -out cd/codesigning.asc -d
        gpg --fast-import cd/codesigning.asc

        if [ "$MODULES" == '' ]; then
            mvn -q deploy -P sign,build-extras,quick --settings cd/mvnsettings.xml -B
        else
            mvn -q deploy -P sign,build-extras,quick --settings cd/mvnsettings.xml -B -pl $MODULES
        fi
    fi
fi