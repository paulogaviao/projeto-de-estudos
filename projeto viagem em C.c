#include<stdio.h>
#include<stdlib.h>
#define TAM_R 3
#define TAM_T 10
  struct trecho_viagem
  {
int tc;
  char cidade_origem[30];
     char destino[30];
       int qtd_pdgio;
         float distancia;
           float valor_pdgio;
   };
   struct trecho_viagem trecho[TAM_T];
            
                                   struct rota_viagem
                                      {   int rt;
								       char origem[30];
								         char destino[30];
								          };
					         struct rota_viagem rota[TAM_R];
		
                   //escopo declaração de variaveis
		int i,,opcao,soma_pdg,maior_dist,num;
		float dist_total,valor_pdg; 
		rota_viagem leitura_rt;
	
		//declarando as funções 						
		//neste bloco encontra-se o cadastro de trechos    
		 void cadastro_trecho()
	        {
	        	 	for(i=0;i<TAM_T;i++)
	        	{
	        printf("\nEste trecho pertence a rota:");
	         scanf("%d",&trecho[i].tc);
	       printf("\nensira cidade origem do trecho:");	
                       scanf("%s",&trecho[i].cidade_origem);
                    printf("\nensira cidade de destino do trecho:");
                      scanf("%s",&trecho[i].destino); 
	      printf("\nensira distancia do trecho:");	 
	         scanf("%f",&trecho[i].distancia);
	     printf("\nensira quantia de pedagios do trecho:");
	         scanf("%d",&trecho[i].qtd_pdgio);
                   printf("\nensira valor total dos pedagios do trecho:");
	         scanf("%f",&trecho[i].valor_pdgio);
			}  
		 }  
	    
						 //neste bloco encontra-se o calculo de rota
		void calculo_rota()
		{    soma_pdg=0; 
		   for(i=0;i<TAM_T;i++)
		   {
			 soma_pdg=soma_pdg+trecho[i].qtd_pdgio;
		    }
			 printf("%d\nquantidade total de pedagios eh:\n",soma_pdg);
			 valor_pdg=0;
		     for(i=0;i<TAM_T;i++)
		    {
			  valor_pdg=valor_pdg+trecho[i].valor_pdgio;
		    }
		      printf("%f\nValor total dos pedagios eh:\n",valor_pdg);
		      dist_total=0;
	          for(i=0;i<TAM_T;i++)
		    { 
			 dist_total=dist_total+trecho[i].distancia;
		    }
			 printf("%f\nDistancia total eh:",dist_total);		  
			 } 
		//neste bloco encontra-se a leitura do trecho
		void mostrar_trecho()
		{	
		   for(i=0;i<TAM_T;i++)
		   {
		  printf("%d\nEste trecho pertence a rota:\n\n",trecho[i].tc);
		  printf("%s\nCidade de origem eh\n\n",trecho[i].cidade_origem);							 
	                printf("%s\nDestino da viagem eh\n\n",trecho[i].destino);							 
		  printf("%f\nDistancia em km desta viagem eh\n\n",trecho[i].distancia);							 
	               printf("%d\nQuantidade de pedagios na viagem eh\n\n",trecho[i].qtd_pdgio);							 
	              printf("%f\nValor total dos pedagios eh\n\n",trecho[i].valor_pdgio);							 
          }
	    }
	    //neste bloco cadastra as rotas do percurso
	    void cadastro_rota()
	    {
	     for(i=0;i<TAM_R;i++)
			      {
			     printf("\nRota: ");
				  scanf("%d",&rota[i].rt); 	
			     printf("\nEnsira a rota de origem:"); 	
			      scanf("%s",&rota[i].origem);
			     printf("\nEnsira destino final da rota:");	
			      scanf("%s",&rota[i].destino);
			      }	
		}
		 //neste bloco faz-se a leitura da rota
	       void  mostrar_rota()
		    {
		    for(i=0;i<TAM_R;i++)
		    {
		  printf("\nMostrar as rotas anexadas\n\n");
		    printf("%d\nrota:\n",rota[i].rt);
		    printf("%s\nCidades de origem:\n",rota[i].origem);
                               printf("%s\nDestino da viagem:\n",rota[i].destino);
				  		  
            }
            }
                          //neste bloco encontra-se o menu do programa
	                     void menu()                                                  
	                 	{
			 printf("\nMenu de viagem.\n Escolha uma opcao:\n");
		               printf("\n[1]-Cadastrar um novo trecho\n ");
			  printf("\n[2]-cadastrar uma nova rota\n");
			  printf("\n[3]-mostrar trechos cadastrados\n");
			  printf("\n[4]-mostrar rotas cadastradas\n");
			  printf("\n[5]-calcular valor total das rotas\n");
			  printf("\n[6]-mostrar os trechos que envolve uma cidade\n");
			  printf("\n[7]-mostrar rota mais longa cadastrada\n");
			  printf("\n[0]-sair\n");
			    }
			//neste bloco cadastra o trecho e retorna um valor 	
		  rota_viagem le_trecho()
		{  
		   rota_viagem aux;
	                 scanf("%s",&aux.origem);
	                scanf("%s",&aux.destino);
	                return aux;
			   } 
			  //neste bloco faz-se a leitura do cadastro retornado acima 
		void mostra_rt(rota_viagem aux)
		  {
		  	printf("%s\n\n ler cidade origem",aux.origem);
		   	printf("%s\n\n ler destino",aux.destino);
		  }	
		     //calculo da distância
		      void calcula_dist()
		   {  
                                           maior_dist=0;
		      	for(i=0;i<TAM_T;i++) 
			    {
			    maior_dist=trecho[0].distancia;	 
		  	    if(trecho[i].distancia>maior_dist)    
			    maior_dist=trecho[i].distancia;	 
			    } 
                                                printf("%d\nmaior distancia registrada eh:\n\n ",maior_dist);
                                               } 
		    
	        
    //autor:Paulo Ricardo Gavião
	//data:  13.04.2019
	//Inicio do programa
	int main()
			{
	    do {
	        menu();
	       printf("\nDigite a opcao:\n\n"); 
           scanf("%d",&opcao);
        switch(opcao) 
        {
           case 1:cadastro_trecho();
           break;
           case 2:cadastro_rota();
           break;
           case 3:mostrar_trecho();
           break;
           case 4:mostrar_rota();
           break;
           case 5:calculo_rota();
           break;
           case 6:mostra_rt(leitura_rt);
           break;
           case 7:calcula_dist();
           break;
       }
          } while(opcao !=0 );
        
//dados para o professor testar o programa
  printf("\n\nRegistro para testes\n ");                                                                              printf("\nRegistro de rotas:rota.1 itaqui/porto alegre,\nrota.2 alegrete/sao jose,\nrota.3 bage/gramado.\n"); 
          printf("\ntrechos das rotas e suas distancias\n");
          printf(“\nrota.1:Itaqui/são borja 100km\n, são borja/santa maria 200km\n, santa maria/porto alegre 300km\n”);
          printf(“\nrota.2:alegrete/rosário 200km, rosário/caxias 150km\n, caxias/Blumenau 400 km, Blumenau/são jose 350 km\n”);
          printf(“\nrota.3:bage/lages 250 km, lages/torres 600 km, torres/gramado 700 km.\n”);
              printf(“\nquantidade de pedagios nos trechos\n);
  printf(“\nrota.1:Itaqui/são borja 0 pdg\n, são borja/santa maria 1 pdg\n, santa maria/porto alegre 3pdg\n”);
          printf(“\nrota.2:alegrete/rosário 1 pdg, rosário/caxias 2 pdg\n, caxias/Blumenau 4 pdg, Blumenau/são jose 2 pdg\n”);
          printf(“\nrota.3:bage/lages 0 pdg, lages/torres 3 pdg, torres/gramado 2 pdg”);
          printf(“\nValor base de cada pedagio eh 5 reais. Ex 3 pedagios 3x5:15reais\n); 
          
         
          system("pause");

			 }
