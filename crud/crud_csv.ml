let create file args =
  let data =
    if Sys.file_exists file then Libcsv.load_csv file
    else []
  in
  let new_data = data @ [args] in
  Libcsv.save_csv file new_data

let read file =
  let data = Libcsv.load_csv file in
  List.iter (fun line ->
    print_endline (String.concat ";" line)
  ) data
