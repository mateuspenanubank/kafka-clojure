(ns kafka-clojure.new-order-main
  (:import (java.util Properties)
           (org.apache.kafka.clients.producer Callback ProducerConfig ProducerRecord KafkaProducer)
           (org.apache.kafka.common.serialization StringSerializer)))


(defn properties []
  (let [props (Properties.)]
    (doto props
      (.setProperty ProducerConfig/BOOTSTRAP_SERVERS_CONFIG "localhost:9092")
      (.setProperty ProducerConfig/KEY_SERIALIZER_CLASS_CONFIG (.getName (.getClass (StringSerializer.))))
      (.setProperty ProducerConfig/VALUE_SERIALIZER_CLASS_CONFIG (.getName (.getClass (StringSerializer.)))) )
    props))


(defn new-order []
  (let [producer (KafkaProducer. (properties))
        callback (reify Callback
                   (onCompletion [this data ex]
                     (if ex
                       (do ; then
                         (println ex))
                       (do ; else
                         (println "sucesso enviando " (.topic data) ":::partition " (.partition data) "/ offset " (.offset data) "/ timestamp " (.timestamp data))))
                     ))
        value "132123,67523,7894589745"
        record (ProducerRecord. "ECOMMERCE_NEW_ORDER" value value)]
    (.get (.send producer record callback)) ))

(new-order)