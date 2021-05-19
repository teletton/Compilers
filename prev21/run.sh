JAVAC=javac
SEP=':'

echo --------
echo cleaning
echo --------
rm -rf bin/prev
make -C src/prev/phase clean
make -C prg/ clean

echo ------------
echo making parser
echo ------------
if make -C src/prev/phase parser SEP=$SEP ; then
	mv src/prev/phase/PrevLexer.interp src/prev/phase/lexan
	mv src/prev/phase/PrevLexer.java src/prev/phase/lexan
	mv src/prev/phase/PrevLexer.tokens src/prev/phase/lexan
	mv src/prev/phase/lexan/PrevLexer.tokens src/prev/phase/synan
	echo ---------
	echo compiling
	echo ---------
	if $JAVAC -cp "lib/antlr-4.9.1-complete.jar${SEP}src/" -d "bin/" src/prev/Compiler.java ; then
		echo -------
		echo testing
		echo -------
		echo test
		echo -------
		make -C prg/ ${1:-'test2'} PHASE=parser SEP=$SEP
		
	fi
fi
