FROM jenkins:latest

ARG PLUGIN_VERSION

ADD ./gitconfig /var/jenkins_home/.gitconfig
ADD target/scm-sync-configuration.hpi /usr/share/jenkins/ref/plugins/scm-sync-configuration.jpi
ADD custom.groovy /usr/share/jenkins/ref/init.groovy.d/custom.groovy

USER root
RUN chown -R jenkins:jenkins /usr/share/jenkins/ref/plugins
RUN /usr/local/bin/install-plugins.sh "scm-sync-configuration:${PLUGIN_VERSION}"

USER jenkins