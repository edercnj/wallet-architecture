#!/bin/bash
curl -L https://istio.io/downloadIstio | ISTIO_VERSION=1.14.1 TARGET_ARCH=x86_64 sh - &&
cd istio-1.14.1/bin && 
./istioctl install --set profile=demo -y  && 
kubectl label namespace default --overwrite=true stio-injection=enabled &&
cd .. && kubectl apply -f samples/addons &&
kubectl rollout status deployment/kiali -n istio-system && 
cd bin && ./istioctl dashboard kiali