# Minimal target Debian root file system
#
# This software is a part of ISAR.
# Copyright (c) Siemens AG, 2018
#
# SPDX-License-Identifier: MIT

Description = "Minimal target Debian root file system"

WORKDIR = "${TMPDIR}/work/${DISTRO}-${DISTRO_ARCH}/${PN}"
DEPLOY_ISAR_BOOTSTRAP = "${DEPLOY_DIR_IMAGE}/isar-bootstrap-${DISTRO}-${DISTRO_ARCH}"
ISAR_BOOTSTRAP_LOCK = "${DEPLOY_DIR_IMAGE}/isar-bootstrap-${DISTRO}-${DISTRO_ARCH}.lock"

include isar-bootstrap.inc

do_generate_keyring[stamp-extra-info] = "${DISTRO}-${DISTRO_ARCH}"

do_apt_config_prepare[stamp-extra-info] = "${DISTRO}-${DISTRO_ARCH}"
do_apt_config_prepare[dirs] = "${WORKDIR}"
do_apt_config_prepare[vardeps] += "\
                                   APTPREFS \
                                   DISTRO_APT_PREFERENCES \
                                   DEBDISTRONAME \
                                   APTSRCS \
                                   DISTRO_APT_SOURCES \
                                   DEPLOY_ISAR_BOOTSTRAP \
                                  "
python do_apt_config_prepare() {
    if not os.path.islink(d.getVar("DEPLOY_ISAR_BOOTSTRAP", True)):
        apt_preferences_out = d.getVar("APTPREFS", True)
        apt_preferences_list = (d.getVar("DISTRO_APT_PREFERENCES", True) or ""
                             ).split()
        aggregate_files(d, apt_preferences_list, apt_preferences_out)

        apt_sources_out = d.getVar("APTSRCS", True)
        apt_sources_list = (d.getVar("DISTRO_APT_SOURCES", True) or "").split()

        aggregate_aptsources_list(d, apt_sources_list, apt_sources_out)
}
addtask apt_config_prepare before do_bootstrap after do_unpack

OVERRIDES_append = ":${@get_distro_needs_https_support(d, False)}"

do_bootstrap[stamp-extra-info] = "${DISTRO}-${DISTRO_ARCH}"
do_bootstrap[vardeps] += "DISTRO_APT_SOURCES"
do_bootstrap() {
    isar_bootstrap
}
addtask bootstrap before do_build after do_generate_keyring

