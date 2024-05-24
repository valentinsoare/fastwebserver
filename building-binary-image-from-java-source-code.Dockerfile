
FROM fedora:39
LABEL maintainer="Valentin Soare <https://github.com/valentinsoare/fastwebserver>"

RUN dnf install unzip zip maven gcc glibc-static libstdc++-static zlib-devel -y

RUN dnf install git -y

RUN git config --global user.name "valentinsoare" \
    && git config --global user.email "soarevalentinn@gmail.com"

RUN mkdir /home/custom-application \
    && cd /home/custom-application \
    && git clone https://github.com/valentinsoare/fastwebserver

RUN chmod +x /home/custom-application/fastwebserver/prepare-building-binary-image.sh

WORKDIR /home/custom-application/fastwebserver/

RUN ./prepare-building-binary-image.sh

CMD ["/bin/bash"]