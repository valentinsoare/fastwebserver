FROM fedora:39
LABEL maintainer="Valentin Soare <https://github.com/valentinsoare/fastwebserver>"

RUN dnf install unzip zip maven gcc glibc-static libstdc++-static zlib-devel git systemd -y

RUN git config --global user.name "valentinsoare" \
    && git config --global user.email "soarevalentinn@gmail.com"

VOLUME application-storage:/home/custom-application

WORKDIR /home/custom-application/fastwebserver

CMD ["/bin/bash"]