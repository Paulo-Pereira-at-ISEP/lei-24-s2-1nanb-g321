set terminal svg size 1920,1080 enhanced font "Arial,20"
set datafile separator ";"

set title "Análise de efficiência do algoritmo da US13"
set xlabel "Nº linhas do ficheiro"
set ylabel "Tempo de execussão (em milisegundos)"
set grid

set style data linespoints

set object 1 rectangle from screen 0,0 to screen 1,1 behind fillcolor rgb "white" fillstyle solid 1.0 noborder

plot "output-files/efficiencyData.csv" u 1:2 smooth unique with lines title 'Eficiência em função do tamanho do ficheiro de input'

set output "output-files/eficiencyPlot.svg"
replot
