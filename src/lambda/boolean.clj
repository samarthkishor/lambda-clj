(ns lambda.boolean)

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
