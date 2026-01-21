# Liste des exécutables à générer
all: main.exe nbjour.exe trierVille.exe 

main.exe: main.ml
	ocamlfind ocamlc -package csv -linkpkg main.ml -o main.exe

nbjour.exe: nbjour.ml
	ocamlopt -o nbjour.exe nbjour.ml

trierVille.exe: trierVille.ml
	ocamlopt -o trierVille.exe trierVille.ml

# Commande de nettoyage complète
clean:
	rm -f *.cmi *.cmx *.o *.obj *.exe *.cmo
	@echo "Nettoyage terminé : seuls les fichiers .ml et le Makefile sont présents."