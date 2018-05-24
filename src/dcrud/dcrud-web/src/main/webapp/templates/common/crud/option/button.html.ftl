<a ${(button.id??)?string('id="'+button.id+'"',"")}
        href="javascript:" class="${button.cls}"
        title="<@spring.message button.title/>"
><@spring.message button.name/></a>