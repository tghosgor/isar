# libsoc library
#
# This software is a part of ISAR.
# Copyright (C) 2018 ilbers GmbH

DESCRIPTION = "libsoc library for ISAR"

LICENSE = "gpl-2.0"
LIC_FILES_CHKSUM = "file://${LAYERDIR_isar}/licenses/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

PV = "0.8.2-0linaro1linarostretch1"
UPSTREAM_VER = "0.8.2"

REPO_LINARO_OVERLAY_STRETCH="http://obs.linaro.org/linaro-overlay-stretch/Debian_9.0"

SRC_URI = "${REPO_LINARO_OVERLAY_STRETCH}/${PN}_${PV}.dsc;destsuffix=${P};unpack=0;md5sum=312b1150e8a0189cd2e88917e5402be8"

SRC_URI += "${REPO_LINARO_OVERLAY_STRETCH}/${PN}_${UPSTREAM_VER}.orig.tar.gz;destsuffix=${P};unpack=0;md5sum=bef73ce0c78a18521abfb232f4b876b5"

SRC_URI += "${REPO_LINARO_OVERLAY_STRETCH}/${PN}_${PV}.debian.tar.xz;destsuffix=${P};unpack=0;md5sum=18692161be90f01479b99745245312a7"

S = "${WORKDIR}/${PN}-${UPSTREAM_VER}"

inherit dpkg

do_unpack_deb_src() {
  rm -rf ${S}
  dpkg-source -x ${WORKDIR}/${PN}_${PV}.dsc ${S}
}
addtask unpack_deb_src after do_unpack before do_install_builddeps
