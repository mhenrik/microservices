$(function () {
    $(".square").on('click', function (event, player) {
        player = "X";
        event.preventDefault(event);
        let id = $(this).attr("id");
        let tiles = $(".square");
        let gamearea = ["-", "-", "-", "-", "-", "-", "-", "-", "-"];
        let icon = $('<i/>', {});
        if(player == "O" && $(this).find('.fa').length != 1){
            icon.attr("class", "fa fa-circle-o");
        } else if (player == "X" && $(this).find('.fa').length != 1) {
            icon.attr("class", "fa fa-times");
        }
        icon.attr("aria-hidden", true);
        $(this).append(icon);

        tiles.each(function (index) {
            let children = $(this).children();
            for (let i = 0; i < children.length; i++){
                let classes = children[i].classList;
                for(let j = 0; j < classes.length; j++){
                    if (classes[j] == "fa-circle-o"){
                        gamearea[index] = "O";
                    } else if (classes[j] == "fa-times"){
                        gamearea[index] = "X";
                    }
                }
            }
        });
        gamearea = gamearea.join("");
        let data = {"table" : gamearea};
        $.ajax({
            type: "POST",
            url: "/game/moves",
            data: data,
            success: function (response) {
                console.log(response);
            },
            error: function (response) {
                console.log(response)
            }
        });

    })
});