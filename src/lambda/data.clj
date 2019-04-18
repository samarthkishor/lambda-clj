(ns lambda.data
  (:require [lambda.boolean :refer :all]))

;;
;; Data (in LISP-like terms)

(def NIL
  "The empty list '()"
  (fn [x]
    TRUE))

(def CONS-S
  "Pair (x, y) which is equivalent to the cons cell (x . y)"
  (fn [x]
    (fn [y]
      (fn [pair]
        ((pair x) y)))))

(defn CONS
  "Function that returns the CONS cell of x and y"
  [x y]
  ((CONS-S x) y))

; passes the boolean TRUE to the CONS function ((CONS 0 1) TRUE) -> 0
(def CAR
  "Returns the first element of the pair"
  (fn [pair]
    (pair
     (fn [x]
       (fn [y]
         x)))))

; passes the boolean FALSE to the CONS function ((CONS 0 1) FALSE) -> 1
(def CDR
  "Returns the last element of the pair"
  (fn [pair]
    (pair
     (fn [x]
       (fn [y]
         y)))))

(def EMPTY?
  "Predicate that checks if a list is empty"
  (fn [l]
    (l
     (fn [x]
       (fn [y]
         FALSE)))))
