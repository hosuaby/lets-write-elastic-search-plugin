# Null plugin for Elasticsearch

Even less than hello-world, this plugin do nothing but compile and run. You can use it as skeleton
for your future plugin.

Try it:
```shell script
$ ../gradlew clean build run
```

And open in web browser http://localhost:9200/_cat/plugins .

You must see:
```text
integTest-0 null-plugin 0.0.0
```
