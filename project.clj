(defproject kafka-clojure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]

                 ;; https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients
                 [org.apache.kafka/kafka-clients "2.8.0"]

                 ;; https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
                 [org.slf4j/slf4j-simple "1.7.32"] ]
  :repl-options {:init-ns kafka-clojure.core})
