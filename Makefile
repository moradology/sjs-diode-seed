js_setup:
	cd static_build && npm install
	cd static_build && webpack
	./sbt "project client" fullOptJS

build: build_js build_jvm

push: build
	docker push moradology/geotrellis-admin-client:latest
	docker push moradology/geotrellis-admin-server:latest

clean:
	./sbt clean
	rm -rf static_build/assets
	rm -rf static_build/node_modules
