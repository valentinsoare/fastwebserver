FROM fedora:39
LABEL maintainer="Valentin Soare <https://github.com/valentinsoare/fastwebserver>"

RUN dnf install unzip zip maven gcc glibc-static libstdc++-static zlib-devel -y

RUN dnf install git -y

RUN git config --global user.name "valentinsoare" \
    && git config --global user.email "soarevalentinn@gmail.com"

RUN mkdir /home/building-binary_environment \
    && cd /home/building-binary_environment \
    && git clone https://github.com/valentinsoare/fastwebserver

RUN chmod +x /home/building-binary_environment/fastwebserver/prepare-building-binary-env.sh

WORKDIR /home/building-binary_environment/fastwebserver/

RUN ./prepare-building-binary-env.sh

CMD ["/bin/bash"]