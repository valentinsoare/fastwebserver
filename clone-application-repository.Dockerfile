FROM fedora:39
LABEL maintainer="Valentin Soare <https://github.com/valentinsoare/fastwebserver>"

RUN dnf install passwd git -y

RUN git config --global user.name "valentinsoare" \
    && git config --global user.email "soarevalentinn@gmail.com"

RUN mkdir cd /home/custom-application && cd /home/custom-application \
    && git clone https://github.com/valentinsoare/fastwebserver

RUN sed -i 's/# %wheel/%wheel/g' /etc/sudoers \
    && useradd -c 'fastwebserver user application' -m -d /home/custom-application/fastwebserver -s /bin/bash fastwebserver \
    && usermod -aG wheel fastwebserver \
    && echo "default" | passwd fastwebserver --stdin \
    && chown -R fastwebserver:fastwebserver /home/custom-application/fastwebserver \
    && chmod 750 /home/custom-application/fastwebserver

CMD ["/bin/bash"]