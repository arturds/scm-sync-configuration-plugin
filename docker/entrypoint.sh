#!/bin/bash

SCM_REPOSITORY=${SCM_REPOSITORY:-}
SCM_BRANCH=${SCM_BRANCH:-}

function scmSyncCurrentBranch() {
    configuration=/var/jenkins_home/scm-sync-configuration.xml
    repository=/var/jenkins_home/scm-sync-configuration/checkoutConfiguration/
    branch=$SCM_BRANCH
    currentBranch=$(git -C $repository rev-parse --abbrev-ref HEAD 2> /dev/null)
    if [ $? = 0 ] ; then
        SCM_BRANCH=$currentBranch

        if [ "$branch" != "$SCM_BRANCH" ] ; then
            BRANCH_INFO="(branch '$branch' foi ignorada porque já existe um repositório clonado no container)"
        fi
    fi

    echo "
------------------------------------------------------------------------------
    SCM-SYNC-CONFIGURATION STARTUP:
        - SCM_REPOSITORY=$SCM_REPOSITORY
        - SCM_BRANCH=$SCM_BRANCH $BRANCH_INFO
------------------------------------------------------------------------------
    "

    if [ ! -f $configuration ] ; then
        echo "<?xml version='1.0' encoding='UTF-8'?>
<hudson.plugins.scm__sync__configuration.ScmSyncConfigurationPlugin version='1'>
  <scm class='hudson.plugins.scm_sync_configuration.scms.ScmSyncGitSCM'/>
  <scmRepositoryUrl>$SCM_REPOSITORY</scmRepositoryUrl>
  <noUserCommitMessage>false</noUserCommitMessage>
  <displayStatus>true</displayStatus>
  <commitMessagePattern>[message]</commitMessagePattern>
  <defaultBranch>$SCM_BRANCH</defaultBranch>
  <manualSynchronizationIncludes/>
</hudson.plugins.scm__sync__configuration.ScmSyncConfigurationPlugin>" > $configuration
    fi
}
scmSyncCurrentBranch

exec /bin/tini -- /usr/local/bin/jenkins.sh