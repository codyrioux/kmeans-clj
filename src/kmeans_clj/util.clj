(ns kmeans-clj.util
  (:require [clojure.core.async :refer [>! <! <!! chan go-loop go]]))

(defn argmin
  "Returns the item in coll which results in the minimal value for f."
  [f coll]
  (when (seq coll)
    (let [results (zipmap coll (map f coll))
          min-value (apply min (vals results))
          min-args (map first (filter #(= min-value (second %)) results))]
      (rand-nth min-args))))

(defn fmap
  "Maps f to each value of m, returning the corresponding map."
  [f m]
  (into {} (for [[k v] m] [k (f v)])))

(defn sink
  "Returns an atom containing a vector. Consumes values from channel
   ch and conj's them into the atom."
  [ch]
  (let [a (atom [])]
    (go-loop []
             (let [val (<! ch)]
               (when-not (nil? val)
                 (swap! a conj val)
                 (recur))))
    a))

(defn parallel
  "Processes values from input channel in parallel on n 'go' blocks.

   Invokes f on values taken from input channel. Values returned from f
   are written on output channel.

   Returns a channel which will be closed when the input channel is
   closed and all operations have completed.

   Note: the order of outputs may not match the order of inputs."
  [n f input output]
  (let [tasks (doall
                (repeatedly n
                            #(go-loop []
                                      (let [in (<! input)]
                                        (when-not (nil? in)
                                          (let [out (f in)]
                                            (when-not (nil? out)
                                              (>! output out))
                                            (recur)))))))]
    (go (doseq [task tasks]
          (<! task)))))
