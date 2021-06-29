// Problem 1
let list = [15; 9; 18; 25; 50]

let findLast list = 
  List.head(List.rev (list))
    
printfn "Problem 1\nWe will reverse the list %A and get the head of the new reversed list.\nLast element of %A = %A" list list (findLast list)

// Problem 2
let mylist = [11..50]

let isMultiple = List.map (fun element -> element % 10 = 0) mylist

printfn "\nProblem 2\nGiven this list {\n%A\n}\nLet us determine if each element in the list is multiple of 10 or not:\n%A" mylist isMultiple