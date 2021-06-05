// Problem 1
let rec findLast list = 
    match list with
      | [x] -> x
      | _ :: tail -> (findLast list)
      | _ -> failwith "Empty list"

printfn "Problem 1\n%A -> Last element is %A"[15; 9; 18; 25; 50] (findLast [15; 9; 18; 25; 50])


