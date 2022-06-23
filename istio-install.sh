#!/bin/bash
curl -L https://istio.io/downloadIstio  > ./istio | ISTIO_VERSION=1.14.1 TARGET_ARCH=x86_64 sh - &&
cd istio-1.14.1/bin && 
./istioctl install --set profile=demo -y