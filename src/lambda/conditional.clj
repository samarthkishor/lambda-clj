(ns lambda.conditional)

;; Conditional Statement

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
