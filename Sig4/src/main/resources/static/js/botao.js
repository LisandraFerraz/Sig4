const botaoMenu = document.querySelector('.cabecalho__munu');
			const menu = document.querySelector('.menu-lateral');
			const EsconderVideo = document.querySelector('.conteudo')
			
							botaoMenu.addEventListener('click', ()=>{
											menu.classList.toggle('menu-lateral--ativo');
											EsconderVideo.classList.toggle('esconder__videos')

							});  