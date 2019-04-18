(ns lambda.core
  (:require [lambda.boolean :refer :all])
  (:require [lambda.data :refer :all])
  (:require [lambda.conditional :refer :all])
  (:require [lambda.numbers :refer :all]))

;;
;; Recursion

(def FIX
  "The Y (Fixed Point) Combinator"
  (fn [f]
    ((fn [x]
       (x x))
     (fn [x]
       (f (fn [y]
            (x x) y))))))

(def YC
  (fn [f]
    ((fn [x]
       (f (fn [z]
            ((x x) z))))
     (fn [x] (f (fn [z]
                  ((x x) z)))))))

;;
;; Sample Programs
 
(def FACTORIAL-attempt
  "How you would write the factorial function in a lazily evaluated lambda calculus"
  (fn [f]
    (fn [n]
      (IF (ZERO? n)
          ONE
          (PROD n (f (DEC n)))))))

(def FACT
  "The factorial function"
  (fn [f]
    (fn [n]
      (IF (ZERO? n)
          (fn [_]
            ONE)
          (fn [_]
            (PROD n (f (DEC n))))))))

(def FACT-CLJ
  "The factorial function in pure Clojure without self-referentiality"
  (fn [f]
    (fn [n]
      (if (zero? n)
        1
        (* n (f (dec n)))))))

(def LISTSUM
  "Returns the sum of the values in a list"
  (FIX
   (fn [f]
     (fn [list]
       (IF (EMPTY? list)
           (fn [_]
             ZERO)
           (fn [_]
             (ADD (CAR list) (f (f (CDR list))))))))))

(def sum-list
  "Pure Clojure version of LISTSUM"
  (FIX
   (fn [f]
     (fn [list]
       (if (empty? list)
         0
         (+ (first list) (f (last list))))))))
