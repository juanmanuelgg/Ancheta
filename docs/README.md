## Welcome to GitHub Pages

You can use the [editor on GitHub](https://github.com/ranchoManuel/Ancheta/edit/master/README.md) to maintain and preview the content for your website in Markdown files.

Whenever you commit to this repository, GitHub Pages will run [Jekyll](https://jekyllrb.com/) to rebuild the pages in your site, from the content in your Markdown files.

### Markdown

Markdown is a lightweight and easy-to-use syntax for styling your writing. It includes conventions for

```markdown
Syntax highlighted code block

# Header 1
## Header 2
### Header 3

- Bulleted
- List

1. Numbered
2. List

**Bold** and _Italic_ and `Code` text

[Link](url) and ![Image](src)
```

For more details see [GitHub Flavored Markdown](https://guides.github.com/features/mastering-markdown/).

### Jekyll Themes

Your Pages site will use the layout and styles from the Jekyll theme you have selected in your [repository settings](https://github.com/ranchoManuel/Ancheta/settings). The name of this theme is saved in the Jekyll `_config.yml` configuration file.

### Support or Contact

Having trouble with Pages? Check out our [documentation](https://help.github.com/categories/github-pages-basics/) or [contact support](https://github.com/contact) and we’ll help you sort it out.

<span id="app"></span>

<script src="https://unpkg.com/react@18/umd/react.production.min.js" crossorigin></script>

<script src="https://unpkg.com/react-dom@18/umd/react-dom.production.min.js" crossorigin></script>

<script src="https://unpkg.com/@bonapata/partes@0.2.11/dist/umd/partes.js" crossorigin></script>

<script>
    const pathnames = new Map();
    pathnames.set('/javascript', 'assets/images/js.png');
    pathnames.set('/mysql', 'assets/images/mysql.png');
    pathnames.set('/git', 'assets/images/git.png');
    pathnames.set('/bash', 'assets/images/gnu-bash.png');
    pathnames.set('/postgresql', 'assets/images/postgres.png');
    pathnames.set('/firewall', 'assets/images/cortafuegos.png');
    pathnames.set('/networking', 'assets/images/networking.png');
    pathnames.set('/docker', 'assets/images/docker.png');
    pathnames.set('/ssh', 'assets/images/ssh.png');
    pathnames.set('/python', 'assets/images/python.png');
    pathnames.set('/linux-basic-info', 'assets/images/linux.png');
    pathnames.set('/java', 'assets/images/java.png');
    pathnames.set('/java-process', 'assets/images/java-process.png');
    pathnames.set('/crontab', 'assets/images/cron.png');
    pathnames.set('/vim', 'assets/images/cli-file.png');
    pathnames.set('/heroku-java', 'assets/images/heroku.png');
    pathnames.set('/gpg', 'assets/images/privacyBorder.png');
    pathnames.set('/strace', 'assets/images/strace.svg');

    const props = { pathnames };
    const element = React.createElement(partes.SpeedDial, props);
    const container = document.getElementById('app');

    ReactDOM.render(element, container);
</script>
