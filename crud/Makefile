# Liste des exécutables à générer
all: bissextile.exe nbjour.exe trierVille.exe

bissextile.exe: bissextile.ml
	ocamlopt -o bissextile.exe bissextile.ml

nbjour.exe: nbjour.ml
	ocamlopt -o nbjour.exe nbjour.ml

trierVille.exe: trierVille.ml
	ocamlopt -o trierVille.exe trierVille.ml

# Commande de nettoyage complète
clean:
	rm -f *.cmi *.cmx *.o *.obj *.exe
	@echo "Nettoyage terminé : seuls les fichiers .ml et le Makefile sont présents."