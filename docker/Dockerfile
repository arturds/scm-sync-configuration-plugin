FROM jenkins:latest

ARG PLUGIN_VERSION

ADD target/scm-sync-configuration.hpi /usr/share/jenkins/ref/plugins/scm-sync-configuration.jpi

USER root
RUN chown -R jenkins:jenkins /usr/share/jenkins/ref/plugins
RUN /usr/local/bin/install-plugins.sh "scm-sync-configuration:${PLUGIN_VERSION}"

USER jenkins
ADD entrypoint.sh /
ENTRYPOINT ["/entrypoint.sh"]
