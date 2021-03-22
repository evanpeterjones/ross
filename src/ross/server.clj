(ns ross.server
  (:require
   [ross.handler :refer [app]]
   [config.core :refer [env]]
   [ring.adapter.jetty :refer [run-jetty]])
  (:import (java.net BindException ServerSocket)
           (java.io IOException))
  (:gen-class))

(defn open? [p]
  (try
    (let [sock (doto (new ServerSocket p)
                 (.setReuseAddress true))]
      (.close sock)
      true)
    (catch IOException e)))

(defn available-port
  ([] (available-port 3000))
  ([p]
   (loop [port p]
     (if (open? port)
       port
       (recur (inc port))))))

(defn -main
  ([] (-main 3000))
  ([p]
   (try
     (let [port (or (env :port) (available-port))
           server (run-jetty #'app {:port port :join? false})]
       (prn (str "Server Started on Port: " port))
       server)
     (catch BindException e
       (prn (str "Port " p " in use, trying " (inc p)))))))
