(ns kafka-clojure.fraud-detector-service
  (:import (org.apache.kafka.clients.consumer ConsumerConfig KafkaConsumer)
           (org.apache.kafka.common.serialization StringDeserializer)
           (java.util Collections Properties)
           (java.time Duration)))

(defn properties []
  (let [props (Properties.)]
    (doto props
      (.setProperty ConsumerConfig/BOOTSTRAP_SERVERS_CONFIG "localhost:9092")
      (.setProperty ConsumerConfig/KEY_DESERIALIZER_CLASS_CONFIG (.getName (.getClass (StringDeserializer.))))
      (.setProperty ConsumerConfig/VALUE_DESERIALIZER_CLASS_CONFIG (.getName (.getClass (StringDeserializer.))))
      (.setProperty ConsumerConfig/GROUP_ID_CONFIG "fraud-detector-service") )
    props))

(defn consume-orders []
  (let [consumer (KafkaConsumer. (properties))]
    (.subscribe consumer (Collections/singletonList "ECOMMERCE_NEW_ORDER"))
    (repeatedly #(do (let [records (.poll consumer (Duration/ofMillis 100))]
                       (if (not (.isEmpty records))
                         (do ;then
                           (println "Encontrei" (.count records) "registros")
                           (doseq [record records]
                             (println "------------------------------------------")
                             (println "Processing new order, checking for fraud")
                             (println (.key record))
                             (println (.value record))
                             (println (.partition record))
                             (println (.offset record))

                             (println "Order processed")
                             
                           ))))))))

(consume-orders)