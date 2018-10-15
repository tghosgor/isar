# mraa library
#
# This software is a part of ISAR.
# Copyright (C) 2018 ilbers GmbH

DESCRIPTION = "mraa library for ISAR"

LICENSE = "gpl-2.0"
LIC_FILES_CHKSUM = "file://${LAYERDIR_isar}/licenses/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

PV = "1.4.0-1linarostretch1"
UPSTREAM_VER = "1.4.0"

REPO_LINARO_OVERLAY_STRETCH="http://obs.linaro.org/linaro-overlay-stretch/Debian_9.0"

SRC_URI = "${REPO_LINARO_OVERLAY_STRETCH}/${PN}_${PV}.dsc;destsuffix=${P};unpack=0;md5sum=f013d2b8b61a2b10c35c0438e7f71461"

SRC_URI += "${REPO_LINARO_OVERLAY_STRETCH}/${PN}_${UPSTREAM_VER}.orig.tar.gz;destsuffix=${P};unpack=0;md5sum=f05ab2bc32e8e1e3fa86dd7dc6b00840"

SRC_URI += "${REPO_LINARO_OVERLAY_STRETCH}/${PN}_${PV}.debian.tar.xz;destsuffix=${P};unpack=0;md5sum=120435917073debf9a592c4351557ace"

S = "${WORKDIR}/${PN}-${UPSTREAM_VER}"

inherit dpkg

DEPENDS = "libsoc"

do_unpack_deb_src() {
  rm -rf ${S}
  dpkg-source -x ${WORKDIR}/${PN}_${PV}.dsc ${S}
}
addtask unpack_deb_src after do_unpack before do_install_builddeps
