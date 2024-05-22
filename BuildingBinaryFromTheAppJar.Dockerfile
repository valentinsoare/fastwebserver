FROM fedora:39
LABEL maintainer="Valentin Soare <https://github.com/valentinsoare/fastwebserver>"

RUN dnf install unzip zip maven passwd gcc glibc-static libstdc++-static zlib-devel -y

RUN curl -s "https://get.sdkman.io" | bash

RUN source $HOME/.sdkman/bin/sdkman-init.sh \
    && sdk install java 17.0.10-amzn \
    && sdk install java 22.3.r17-nik

RUN dnf install git -y

RUN git config --global user.name "valentinsoare" \
    && git config --global user.email "soarevalentinn@gmail.com"

RUN mkdir /home/building-binary_environment\
    && cd /home/building-binary_environment \
    && git clone https://github.com/valentinsoare/fastwebserver

WORKDIR /home/building-binary_environment/fastwebserver

RUN source $HOME/.sdkman/bin/sdkman-init.sh \
    && sdk use java 22.3.r17-nik \
    && mvn -Pnative -DskipNativeTests native:compile