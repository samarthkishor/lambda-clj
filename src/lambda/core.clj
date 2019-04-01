(ns lambda.core)

;; https://www.youtube.com/watch?v=MAB11qXZnuU ["Making a usable language from Lambda Calculus"] was super helpful

;;
;; Boolean Algebra

(def TRUE
  "The boolean value for TRUE"
  (fn [x]
    (fn [y]
      x)))

(def FALSE
  "The boolean value for FALSE"
  (fn [x]
    (fn [y]
      y)))

(def AND-S
  "The boolean AND operation"
  (fn [x]
    (fn [y]
      ((x y) x))))

;; Clojure doesn't simulate currying so this function
;; makes it easier to write
(defn AND
  "The AND function"
  [x y]
  ((AND-S x) y))

(def OR-S
  "The boolean OR operation"
  (fn [x]
    (fn [y]
      ((x x) y))))

(defn OR
  "The OR function"
  [x y]
  ((OR-S x) y))

(def NOT
  "The boolean NOT operation"
  (fn [x]
    ((x FALSE) TRUE)))

(def XOR-S
  "The boolean XOR operation"
  (fn [x]
    (fn [y]
      ((x (NOT y))
       y))))

(defn XOR
  "The XOR function"
  [x y]
  ((XOR-S x) y))

(def IF-S
  "The IF condition"
  (fn [condition]
    (fn [accept]
      (fn [reject]
        ((condition accept) reject)))))

(defn IF
  "The IF function"
  [condition accept reject]
  (((IF-S condition) accept) reject))

;;
;; Numbers

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
  (fn [s]
    (fn [z]
      (s (s z)))))

(def THREE
  (SUCC TWO))

(def SUCC
  "The successor function, returns n + 1"
  (fn [w]
    (fn [y]
      (fn [x]
        (y ((w y) x))))))

(def DEC
  "The predecessor function, returns n - 1"
  (fn [n]
    (CAR (n (TRANS (CONS ZERO ZERO))))))

(def TRANS
  "The transform function, when given a pair (n, n) returns the pair (n, n + 1)"
  (fn [n]
    (CONS (CDR n) (SUCC (CDR n)))))

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

;;
;; Data (in LISP-like terms)

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

;;
;; Recursion

(def FIX
  "The Y (Fixed Point) Combinator"
  (fn [f]
    (fn [x]
      ((f (fn [y]
            ((x x) y)))
       (fn [x]
         (f (fn [y]
              ((x x) y))))))))
