// Part 1
let add a b = a + b
printfn "\nPart 1:\n%d + %d = %d " 1 2 (add 1 2)

let add a b c d = a + b + c + d
printfn "Part 1.2\n%d + %d + %d + %d = %d" 18 34 23 19 (add 18 34 23 19)

//==================================================================================================
// Part 2
// a
let round number =   
    if number >= 100 then 100
    elif number < 0 then 0
    else number
printfn "\nPart 2a\n%d rounding it up to %d" 1102 (round 1102)

// b
let round number = 
    match number with 
    | i when i >= 100 -> 100
    | i when i < 0 -> 0
    | _ -> number
printfn "\nPart 2b\n%i rounding it up to %i" 1102 (round 1102)

//==================================================================================================
// 3
let rec factorial x = 
    match x with
    | 0 | 1 -> 1
    |_ -> x * factorial(x - 1)   
printfn "\nPart 3\n%d! = %d" 6 (factorial 6)

//==================================================================================================
// 4
let list = [1; 2; 3; 4; 5]
let sum list =
   let rec loop list acc =
       match list with
       | head :: tail -> loop tail (acc + head)
       | [] -> acc
   loop list 0
printfn "\nPart 4\nSumming up each element of this list: %A\nThe sum is =  %A" list (sum list)
//==================================================================================================   
// 5 
let list1 = [1; 2; 3]
printfn "\nPart 5"
printfn "list is Empty:  %b" list1.IsEmpty
printfn "list’s Length: %d" list1.Length
printfn "First element of list: %d" list1.Head
printfn "Second element if list: %d" (list1.Item(1))
printfn "Third element of list: %d"  (list1.Item(2))
printfn "Last element of list: complte %d" (list1.Item(2))
