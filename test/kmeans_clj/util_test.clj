(ns kmeans-clj.util-test
  (:require [clojure.test :refer :all]
            [kmeans-clj.util :refer :all]))

(deftest test-argmin
  (testing "Argmin returns nil on empty input."
    (is (nil? (argmin identity []))))
  (testing "Argmin correctly selects minimal element."
    (is (= 5 (argmin identity [9 5 9 8 6 7 10 100000])))
    (is (= -10 (argmin identity [1 4 2 5 1 2 1 -10 -5 -1 0 999999])))))

(deftest test-fmap
  (testing "fmap behaves on empty list."
    (is (= (fmap identity {}) {})))
  (testing "fmap correctly maps inc to numbered list."
    (is (= (fmap inc {:a 0 :b 0 :c 0}) {:a 1 :b 1 :c 1}))))
