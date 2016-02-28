(ns luminus.repl-server
  (:require [clojure.tools.nrepl.server :as nrepl]
            [clojure.tools.logging :as log]))

(defn start
  "Start a network repl for debugging on specified port followed by
  an optional parameters map. The :bind, :transport-fn, :handler,
  :ack-port and :greeting-fn will be forwarded to
  clojure.tools.nrepl.server/start-server as they are."
  [{:keys [port bind transport-fn handler ack-port greeting-fn]}]
  (try
    (nrepl/start-server :port port
                        :bind bind
                        :transport-fn transport-fn
                        :handler handler
                        :ack-port ack-port
                        :greeting-fn greeting-fn)
    (log/info "nREPL server started on port" port)
    (catch Throwable t
      (log/error t "failed to start nREPL"))))

(defn stop [server]
  (nrepl/stop-server server)
  (log/info "nREPL server stopped"))

