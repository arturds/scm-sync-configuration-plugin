package hudson.plugins.scm_sync_configuration.model;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import hudson.plugins.scm_sync_configuration.scms.SCM;

public class ScmContext {

	private String scmRepositoryUrl;
	private SCM scm;
	private String commitMessagePattern;
	private String defaultBranch;

    public ScmContext(SCM _scm, String _scmRepositoryUrl){
        this(_scm, _scmRepositoryUrl, "[message]");
    }

	public ScmContext(SCM _scm, String _scmRepositoryUrl, String _commitMessagePattern){
		this(_scm, _scmRepositoryUrl, _commitMessagePattern, null);
	}

	public ScmContext(SCM _scm, String _scmRepositoryUrl, String _commitMessagePattern, String _defaultBranch){
		this.scm = _scm;
		this.scmRepositoryUrl = _scmRepositoryUrl;
		this.commitMessagePattern = _commitMessagePattern;
		this.defaultBranch = StringUtils.defaultIfBlank(_defaultBranch, "master");
	}

	public String getScmRepositoryUrl() {
		return scmRepositoryUrl;
	}

	public SCM getScm() {
		return scm;
	}

    public String getCommitMessagePattern(){
        return commitMessagePattern;
    }

	public String getDefaultBranch() {
		return defaultBranch;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("scm", scm).append("scmRepositoryUrl", scmRepositoryUrl)
                .append("commitMessagePattern", commitMessagePattern).toString();
	}
}
