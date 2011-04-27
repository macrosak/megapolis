
%{
clear all;
close all;

d = dir;

figure
hold on

for i=1:length(d)
    
    if ~d(i).isdir || strcmp(d(i).name, '.') || strcmp(d(i).name, '..')
        continue;
    end
    
    if isempty(regexpi(d(i).name, '(^road-|^Tcrossroad|^crossroad|background|build)'))
        subplot(6,6,i)
        im = imread([d(i).name '/iso.png']);
        imshow(im); 
    end
    
end

%}

clear all;
close all;

im = imread('office2/iso.png');
imagesc(im);

[x, y] = ginput; % enter ukonci zadavani

x1 = x;
y1 = y;

for i=1:3
    fprintf('mapx: [');
    fprintf('%d,', round(x));
    fprintf(']\n');

    fprintf('mapy: [');
    fprintf('%d,', round(y));
    fprintf(']\n\n');

    x = x / 2;
    y = y / 2;
end

hold on;

plot(x1,y1,'-r', 'LineWidth', 2);









