import jenkins.model.*
import hudson.plugins.scm_sync_configuration.ScmSyncConfigurationPlugin

def ScmSyncConfigurationPlugin scmSyncPlugin = Jenkins.instance.getPlugin(ScmSyncConfigurationPlugin.class)
def env = System.getenv()
def scmRepository = env['SCM_REPOSITORY']
def scmBranch = env['SCM_BRANCH']
def scmConfiguration = new File("/var/jenkins_home/scm-sync-configuration.xml")
def scmCloneRepository = new File("/var/jenkins_home/scm-sync-configuration/checkoutConfiguration/")
def currentBranch = scmBranch
def branchMsgWarn = ""
def scmWasConfigured = null

if (scmCloneRepository.exists()) {
    currentBranch = "git rev-parse --abbrev-ref HEAD".execute(null, scmCloneRepository).text
    if (!scmBranch.equals(currentBranch)) {
        branchMsgWarn = "         !!! (branch '${scmBranch}' foi ignorada porque já existe um repositório clonado no container)"
    }
}

if (!scmConfiguration.exists()) {
    def xml = """<?xml version='1.0' encoding='UTF-8'?>
<hudson.plugins.scm__sync__configuration.ScmSyncConfigurationPlugin version='1'>
    <scm class='hudson.plugins.scm_sync_configuration.scms.ScmSyncGitSCM'/>
    <scmRepositoryUrl>${scmRepository}</scmRepositoryUrl>
    <noUserCommitMessage>false</noUserCommitMessage>
    <displayStatus>true</displayStatus>
    <commitMessagePattern>[message]</commitMessagePattern>
    <defaultBranch>${currentBranch}</defaultBranch>
    <manualSynchronizationIncludes/>
</hudson.plugins.scm__sync__configuration.ScmSyncConfigurationPlugin>
"""
    scmConfiguration.withWriter('UTF-8') { writer ->
        writer.write(xml)
    }
    scmWasConfigured = true
} else {
    scmWasConfigured = false
}

println """
------------------------------------------------------------------------------
    SCM-SYNC-CONFIGURATION STARTUP:
        - SCM_REPOSITORY=${scmRepository}
        - SCM_BRANCH=${currentBranch} ${branchMsgWarn}
    SCM-SYNC-CONFIGURATION RELOAD FILES...
------------------------------------------------------------------------------
"""
scmSyncPlugin.start()
scmSyncPlugin.reloadAllFilesFromScm()
if(scmWasConfigured) {
    Jenkins.instance.restart()
}
