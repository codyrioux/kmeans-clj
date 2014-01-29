(ns kmeans-clj.sample
  (:require [kmeans-clj.core :refer [k-means]]
            [incanter.stats :refer [euclidean-distance]]))

(defn features
  [x]
  (assoc (vec (repeat 10 0)) x 1))

(defn -main
  []
  (prn
    (k-means
      [0 0 0 1 1 1 2 2 2 4 5 6 8 9 9 8 1 2 5 8 2 1 4 0 5 2 4
       1 2 4 5 6 7 0 1 2 3 8 3 2 4 5 6 7 1 2 4 0 1 2 4 1 5 0
       9 8 7 8 9 8 9 8 9 9 9 9 1 2 4 5 9 0 2 1 4 0 5 1 2 2 2
       0 0 0 0 0 0 0 0 0 1 2 4 5 6 1 2 3 4 5 6 1 2 4 5 6 1 8]
      euclidean-distance
      features
      15
      100)))
