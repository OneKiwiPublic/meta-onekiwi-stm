SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Recipe created by bitbake-layers"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
TARGET_CC_ARCH += "${LDFLAGS}"

inherit update-rc.d
INITSCRIPT_NAME = "logo-ascii-script"
INITSCRIPT_PARAMS = "defaults 90 10"


SRC_URI = "file://logo-ascii.c"
SRC_URI += "file://logo-ascii-script"

S = "${WORKDIR}"

do_compile() {
    ${CC} logo-ascii.c -o logo-ascii
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 logo-ascii ${D}${bindir}

    # Install startup script
    install -d ${D}/etc/init.d
    install -m 0755 logo-ascii-script ${D}/etc/init.d
}

# Add the startup script to the list of files to be packaged.  Any files
# that are installed but not packaged will cause a warning to be printed
# during the bitbake process.
FILES_${PN} += "/etc/init.d/*"
