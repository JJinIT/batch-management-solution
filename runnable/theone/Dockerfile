FROM ubuntu:latest

RUN apt-get -y update
RUN apt-get -y install default-jdk

COPY ./ /home/sns

#ADD https://downloads.apache.org/kafka/2.6.0/kafka_2.13-2.6.0.tgz /home/kafka/
#RUN tar xvzf /home/kafka/kafka_* -C /home/kafka/

#RUN apt-get -y install docker.io
#RUN apt-get -y install docker-compose

RUN apt-get install -y software-properties-common
RUN add-apt-repository ppa:cwchien/gradle
RUN apt-get -y install gradle

RUN cd /home/sns/ && gradle

WORKDIR /home/sns
CMD ["/home/sns/gradlew", ":sw-subscribe:bootRun", "-Dserver.port=8090"]

