#!/bin/bash
pdflatex -synctex=1 -interaction=nonstopmode -file-line-error -output-directory=./out main.tex
pdflatex -synctex=1 -interaction=nonstopmode -file-line-error -output-directory=./out main.tex