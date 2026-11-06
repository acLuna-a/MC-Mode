"""从 src/main/resources 与 build/classes 打包出可分发的模组 jar。

用法：先运行 compile.bat 编译源码，再 python tools/build.py
"""
import os
import zipfile
from datetime import datetime

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
RES = os.path.join(ROOT, "src", "main", "resources")
CLASSES = os.path.join(ROOT, "build", "classes")
OUT = os.path.join(ROOT, "车万女仆-神创之约god-trident-1.0.0.jar")


def main():
    if not os.path.isdir(CLASSES):
        raise SystemExit("缺少 build/classes，请先运行 tools/compile.bat")

    entries = []
    for base in (RES, CLASSES):
        for root, _, files in os.walk(base):
            for fn in files:
                full = os.path.join(root, fn)
                rel = os.path.relpath(full, base).replace(os.sep, "/")
                entries.append((rel, full))
    entries.sort(key=lambda e: e[0])

    dirs = set()
    for rel, _ in entries:
        parts = rel.split("/")[:-1]
        for i in range(1, len(parts) + 1):
            dirs.add("/".join(parts[:i]) + "/")

    with zipfile.ZipFile(OUT, "w", zipfile.ZIP_DEFLATED) as z:
        for d in sorted(dirs):
            z.writestr(d, b"")
        for rel, full in entries:
            with open(full, "rb") as f:
                z.writestr(rel, f.read())
    print("built:", OUT, os.path.getsize(OUT), "bytes,", len(entries), "files")


if __name__ == "__main__":
    main()
