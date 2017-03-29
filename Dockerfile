FROM instructure/scala-sbt

ADD . /code
WORKDIR /code
USER root
RUN apt-get -y update
RUN sbt compile

