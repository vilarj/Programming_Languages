// Problem 1
let rec findLast list = 
    match list with
      | [x] -> x
      | _ :: tail -> (findLast list)
      | _ -> failwith "Empty list"

printfn "Problem 1\nLast element in the list %A is %A"[15; 9; 18; 25; 50] (findLast [15; 9; 18; 25; 50])

// Problem 2
let mylist = [11..50]