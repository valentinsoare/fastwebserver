FROM fedora:39
LABEL maintainer="Valentin Soare <https://github.com/valentinsoare/fastwebserver>"

RUN dnf install unzip zip maven passwd gcc glibc-static libstdc++-static zlib-devel git -y

RUN git config --global user.name "valentinsoare" \
    && git config --global user.email "soarevalentinn@gmail.com"

RUN mkdir /home/building-binary_environment \
    && cd /home/building-binary_environment \
    && git clone https://github.com/valentinsoare/fastwebserver

RUN chmod +x /home/building-binary_environment/fastwebserver/prepare-building-binary-env.sh

WORKDIR /home/building-binary_environment/fastwebserver/

CMD ["/bin/bash", "-c", "./prepare-building-binary-env.sh"]