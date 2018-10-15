# upm library
#
# This software is a part of ISAR.
# Copyright (C) 2018 ilbers GmbH

DESCRIPTION = "upm library for ISAR"

LICENSE = "gpl-2.0"
LIC_FILES_CHKSUM = "file://${LAYERDIR_isar}/licenses/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

PV = "0.8.0-1linarostretch1"
UPSTREAM_VER = "0.8.0"

REPO_LINARO_OVERLAY_STRETCH="http://obs.linaro.org/linaro-overlay-stretch/Debian_9.0"

SRC_URI = "${REPO_LINARO_OVERLAY_STRETCH}/${PN}_${PV}.dsc;destsuffix=${P};unpack=0;md5sum=665a90f25b38d65631bc7ffc21b36d9a"

SRC_URI += "${REPO_LINARO_OVERLAY_STRETCH}/${PN}_${UPSTREAM_VER}.orig.tar.gz;destsuffix=${P};unpack=0;md5sum=9de4b3b101460f3aef57e701588d6e8d"

SRC_URI += "${REPO_LINARO_OVERLAY_STRETCH}/${PN}_${PV}.debian.tar.xz;destsuffix=${P};unpack=0;md5sum=62a9aaf11e8aee42a3a9d34611f171ec"

S = "${WORKDIR}/${PN}-${UPSTREAM_VER}"

inherit dpkg

DEPENDS = "mraa"

do_unpack_deb_src() {
  rm -rf ${S}
  dpkg-source -x ${WORKDIR}/${PN}_${PV}.dsc ${S}
}
addtask unpack_deb_src after do_unpack before do_install_builddeps
