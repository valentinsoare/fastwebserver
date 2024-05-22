FROM fedora:39
LABEL maintainer="Valentin Soare <https://github.com/valentinsoare/fastwebserver>"

RUN dnf install unzip zip maven passwd gcc glibc-static libstdc++-static zlib-devel git -y

RUN mkdir /home/building-binary_environment \
    && cd /home/building-binary_environment \
    && git clone https://github.com/valentinsoare/fastwebserver

RUN git config --global user.name "valentinsoare" \
    && git config --global user.email "soarevalentinn@gmail.com"

WORKDIR /home/building-binary_environment/fastwebserver/scripts

RUN chmod +x /home/building-binary_environment/fastwebserver/scripts/prepare-building-binary-env.sh

CMD ["/home/building-binary_environment/fastwebserver/scripts/prepare-building-binary-env.sh"]