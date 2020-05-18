FROM fabric8/java-alpine-openjdk8-jdk

USER root

# add jar into deployment folder
ARG JAR_FILE
ADD ${JAR_FILE} /deployments/todolist-api.jar

# authenticate jolokia by openshift
ENV AB_JOLOKIA_AUTH_OPENSHIFT true

# access for none priviliged user execution
RUN chmod -R +x /deployments/
RUN chmod -R g+rwX /deployments/