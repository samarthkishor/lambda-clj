(ns lambda.numbers
  (:require [lambda.data :refer :all]
            [lambda.boolean :refer :all]))

;;
;; Numbers

(def SUCC
  "The successor function, returns n + 1"
  (fn [w]
    (fn [y]
      (fn [x]
        (y ((w y) x))))))

(def ZERO
  "The number 0"
  (fn [s]
    (fn [z]
      z)))

(def ONE
  "The number 1"
  (fn [s]
    (fn [z]
      (s z))))

(def TWO
  "The number 2"
  (fn [s]
    (fn [z]
      (s (s z)))))

(def THREE
  "The number 3"
  (SUCC TWO))

(def TRANS
  "The transform function, when given a pair (n, n) returns the pair (n, n + 1)"
  (fn [n]
    (CONS (CDR n) (SUCC (CDR n)))))

(def DEC
  "The predecessor function, returns n - 1"
  (fn [n]
    (CAR (n (TRANS (CONS ZERO ZERO))))))

(def ADD-S
  "The addition function definition"
  (fn [x]
    (fn [y]
      ((y SUCC) x))))

(defn ADD
  "The addition function"
  [x y]
  ((ADD-S x) y))

(def SUM
  "Another definition of ADD using only lambdas"
  (fn [m]
    (fn [n]
      (fn [f]
        (fn [x]
          ((m f) ((n f) x)))))))

(def SUB-S
  "The subtraction function definition"
  (fn [x]
    (fn [y]
      ((y DEC) x))))

(defn SUB
  "The subtraction function"
  [x y]
  ((SUB-S x) y))

(def PROD-S
  "The multiplication function definition"
  (fn [x]
    (fn [y]
      (fn [f]
 
(defn PROD
  "The multiplication function"
  [x y]
  ((PROD-S x) y))
       (x (y f))))))

(def EXP-S
  "The exponentiation function definition"
  (fn [x]
    (fn [b]
      (b x))))

(defn EXP
  "The exponentiation function"
  [x b]
  ((EXP-S x) b))

(def ZERO?
  "Predicate function that checks if a number is zero"
  (fn [x]
    (((x FALSE) NOT) FALSE)))

(def LEQ-S?
  "Less than or equal function definition"
  (fn [x]
    (fn [y]
      (ZERO? (SUB x y)))))

(defn LEQ?
  "Less than or equal function"
  [x y]
  ((LEQ-S? x) y))

(def EQUAL-S?
  "Predicate function definition that checks if two numbers are equal"
  (fn [x]
    (fn [y]
      (AND
       (LEQ? x y)
       (LEQ? y x)))))

(defn EQUAL?
  "Numerical equality predicate function"
  [x y]
  ((EQUAL-S? x) y))
